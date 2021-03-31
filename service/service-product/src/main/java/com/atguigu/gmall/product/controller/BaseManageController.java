package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.BaseManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "商品基础属性接口")
@RestController
@RequestMapping("admin/product")
public class BaseManageController {

    @Autowired
    private BaseManageService baseManageService;

    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> list = baseManageService.getCategory1();
        return Result.ok(list);
    }

    @GetMapping("/getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> list = baseManageService.getCategory2(category1Id);
        return Result.ok(list);
    }

    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> list = baseManageService.getCategory3(category2Id);
        return Result.ok(list);
    }

    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable Long category1Id,
                                                   @PathVariable Long category2Id,
                                                   @PathVariable Long category3Id) {
        List<BaseAttrInfo> list = baseManageService.attrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(list);
    }

    @ApiOperation("获取spu分页列表")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<SpuInfo>> getSpuInfo(@PathVariable long page, @PathVariable long limit, long category3Id) {
        IPage<SpuInfo> spuInfoIPage = baseManageService.getSpuInfo(page,limit,category3Id);
        return Result.ok(spuInfoIPage);
    }

    @ApiOperation("获取销售属性")
    @GetMapping("/baseSaleAttrList")
    public Result<List<BaseSaleAttr>> getBaseSaleAttr(){
        List<BaseSaleAttr> list = baseManageService.getBaseSaleAttr();
        return Result.ok(list);
    }

    @GetMapping("/baseTrademark/getTrademarkList")
    public Result<List<BaseTrademark>> getTrademarkList(){
        List<BaseTrademark> list = baseManageService.getTrademarkList();
        return Result.ok(list);
    }

    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        baseManageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    @Value("${image.url}")
    private String imageUrl;

    @PostMapping("/fileUpload")
    public Result<String> fileUpload(MultipartFile file) throws Exception{

        //加载配置文件tracker.conf
        String allPath = ClassUtils.getDefaultClassLoader().getResource("tracker.conf").getPath();
        //1.初始化配置文件
        ClientGlobal.init(allPath);
        //2.连接Tracker跟踪器，获取存储节点的地址
        TrackerClient trackerClient = new TrackerClient();
        //3.tracker跟踪器 服务器，返回的地址。
        TrackerServer trackerServer = trackerClient.getConnection();
        //4.连接存储节点
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        //获取扩展文件名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //5.保存文件并返回文件路径
        String path = storageClient1.upload_file1(file.getBytes(), extension, null);
        return Result.ok(imageUrl + path);
    }
}
