package com.hc.demo.hc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.demo.hc.entity.File;
import com.hc.demo.hc.mapper.FileMapper;
import com.hc.demo.hc.service.IFileService;
import org.springframework.stereotype.Service;

@Service
public class IFileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {
}
