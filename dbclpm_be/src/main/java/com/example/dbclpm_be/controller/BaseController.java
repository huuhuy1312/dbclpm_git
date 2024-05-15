package com.example.dbclpm_be.controller;

import com.example.dbclpm_be.entity.Address;
import com.example.dbclpm_be.entity.Base;
import com.example.dbclpm_be.respository.AddressRespository;
import com.example.dbclpm_be.respository.BaseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.request.AddBaseRequest;

import java.util.List;
import java.util.Optional;
import java.text.Normalizer;
@RestController
@RequestMapping("api/base")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseController {
    @Autowired
    private BaseRespository baseRespository;


    @GetMapping("/all")
    public ResponseEntity<?> getAllBase()
    {
        List<Base> bases = baseRespository.findAll();
        return ResponseEntity.ok(bases);
    }
}
