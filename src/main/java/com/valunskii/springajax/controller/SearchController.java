package com.valunskii.springajax.controller;

import com.valunskii.springajax.model.AjaxResponseBody;
import com.valunskii.springajax.model.SearchCriteria;
import com.valunskii.springajax.model.User;
import com.valunskii.springajax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        //if error, just return a 400 bad request, along vith the error message
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<User> users = userService.findByUsernameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMessage("no user found!");
        } else {
            result.setMessage("success");
        }

        result.setResult(users);
        return ResponseEntity.ok(result);
    }
}
