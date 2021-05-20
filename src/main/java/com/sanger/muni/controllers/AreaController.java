package com.sanger.muni.controllers;

import com.sanger.muni.model.Area;
import com.sanger.muni.services.AreaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
public class AreaController extends BaseController<Area, Long, AreaService> {

}
