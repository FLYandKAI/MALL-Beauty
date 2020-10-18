package images.service.impl;


import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.discovery.converters.Auto;
import images.entity.ComImg;
import images.entity.ComImgVO;
import images.entity.IntiImg;
import images.entity.IntiImgVO;
import images.mapper.IntiImgMapper;
import images.service.IntiImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.FastDFSUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 帖子图片信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Service
public class IntiImgServiceImpl extends ServiceImpl<IntiImgMapper, IntiImg> implements IntiImgService {


    @Autowired
    private IntiImgMapper intiImgMapper;

    //全局变量--用来存放fastDFS服务器地址IP
    @Value("${imagesAddress}")
    private String imagesAddress;

    @Override
    public CommonResult addImg(IntiImg intiImg) {
        //如果添加数据缺少，则返回
        if (intiImg.getIntiId() == null) return CommonResult.failed("未输入帖子编号");
        if (intiImg.getByWho() == null) return CommonResult.failed("未输入用户编号");

        // System.out.println(comImg.toString());
        //添加
        int insert = intiImgMapper.insert(intiImg);
        if (insert != 1) CommonResult.failed("未添加帖子图片");
        return CommonResult.success("成功添加一张帖子图片");
    }

    @Override
    public CommonResult deleteImg(Long IntiImgId, Long userId) {
        //如果删除数据缺少，则返回
        if (IntiImgId == null) return CommonResult.failed("未输入帖子图片编号");
        if (userId == null) return CommonResult.failed("未输入用户编号");
        //通过帖子图片编号查询一张图片(这张图片必须在服务器中存在)
        IntiImg intiImg = intiImgMapper.selectById(IntiImgId);

        if(intiImg == null) return CommonResult.failed("不存在此图片");

        //如果数据库中保存了图片地址（但是并没有判断格式），且如果保存到了服务器的图片设置了init_img_address长度绝对大于30
        if(intiImg.getIntiImgAddress() != null
                && intiImg.getIntiImgGroup() != null
                && intiImg.getIntiImgAddress().length() > 30){
            //删除服务器中的图片
            FastDFSUtil.delete(intiImg.getIntiImgGroup(), intiImg.getIntiImgAddress());
        }
        //更新用户编号（直接通过id的方式会改不了用户编号）
        intiImg.setByWho(userId);
        int update = intiImgMapper.updateById(intiImg);

        //删除数据库的数据（逻辑删除）
        int delete = intiImgMapper.deleteById(IntiImgId);
        if (delete != 1 || update != 1) return CommonResult.failed("未删除此图片");
        return CommonResult.success("已删除一张图片");
    }

    @Override
    public CommonResult findAllById(Long inti_id) {
        //条件构造器
        QueryWrapper<IntiImg> wrapper = new QueryWrapper<>();
        //通过帖子编号查询所属的全部图片
        wrapper.eq("inti_id", inti_id);
        List<IntiImg> intiImgs = intiImgMapper.selectList(wrapper);
        //查询是否存在此帖子ID
        if (intiImgs.isEmpty()) return CommonResult.failed("此帖子没有图片");
        //最终返回
        List<IntiImgVO> intiImgVOsList = new ArrayList<>();

//拼接图片地址
        for (IntiImg intiImg : intiImgs) {
            //创建intiImg的VO，用于存取拼接的对象（将IntiImg的组名与服务器地址拼接）
            IntiImgVO intiImgVO = new IntiImgVO();
            //将ComImg给comImgVO
            intiImgVO.setIntiImgId(intiImg.getIntiImgId());
            intiImgVO.setIntiId(intiImg.getIntiId());
            intiImgVO.setAddress(imagesAddress + "/" + intiImg.getIntiImgGroup() + "/" + intiImg.getIntiImgAddress());
            intiImgVO.setIntiImgName(intiImg.getIntiImgName());
            intiImgVO.setCreateTime(intiImg.getCreateTime());
            intiImgVO.setUpdateTime(intiImg.getUpdateTime());
            intiImgVO.setDeleted(intiImg.getDeleted());
            intiImgVO.setLastVersion(intiImg.getLastVersion());
            intiImgVO.setByWho(intiImg.getByWho());
            intiImgVOsList.add(intiImgVO);
        }
        return CommonResult.success(intiImgVOsList);
    }

    @Override
    public CommonResult findAllById(Long inti_id, Integer currentPage, Integer currentSize) {
        //条件构造器
        QueryWrapper<IntiImg> wrapper = new QueryWrapper<>();
        //通过帖子编号查询所属的全部图片
        wrapper.eq("inti_id",inti_id);

        // 参数一：当前页
        // 参数二：页面大小
        Page<IntiImg> page = new Page<>(currentPage, currentSize);
        intiImgMapper.selectPage(page, wrapper);
        if(page.getRecords().isEmpty()){
            return CommonResult.failed("没有图片");
        }

        //最终返回
        List<IntiImgVO> intiImgVOs = new ArrayList<>();

//拼接图片地址
        for (IntiImg intiImg : page.getRecords()) {
        //创建intiImg的VO，用于存取拼接的对象（将IntiImg的组名与服务器地址拼接）
            IntiImgVO intiImgVO = new IntiImgVO();
            //将ComImg给comImgVO
            intiImgVO.setIntiImgId(intiImg.getIntiImgId());
            intiImgVO.setIntiId(intiImg.getIntiId());
            intiImgVO.setAddress(imagesAddress + "/" + intiImg.getIntiImgGroup() + "/" + intiImg.getIntiImgAddress());
            intiImgVO.setIntiImgName(intiImg.getIntiImgName());
            intiImgVO.setCreateTime(intiImg.getCreateTime());
            intiImgVO.setUpdateTime(intiImg.getUpdateTime());
            intiImgVO.setDeleted(intiImg.getDeleted());
            intiImgVO.setLastVersion(intiImg.getLastVersion());
            intiImgVO.setByWho(intiImg.getByWho());
            // System.out.println("===========================");
            // System.out.println(comImgVO);
            // System.out.println("===========================");
            intiImgVOs.add(intiImgVO);
        }
        return CommonResult.success(intiImgVOs);
    }
}
