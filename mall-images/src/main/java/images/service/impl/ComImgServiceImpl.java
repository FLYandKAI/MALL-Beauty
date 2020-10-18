package images.service.impl;

import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import images.entity.ComImg;
import images.entity.ComImgVO;
import images.mapper.ComImgMapper;
import images.mapper.IntiImgMapper;
import images.service.ComImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.FastDFSUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品图片信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Service
public class ComImgServiceImpl extends ServiceImpl<ComImgMapper, ComImg> implements ComImgService {
    @Autowired
    private ComImgMapper comImgMapper;

    //全局变量--用来存放fastDFS服务器地址IP
    @Value("${imagesAddress}")
    private String imagesAddress;

    @Override
    public CommonResult addImg(ComImg comImg) {
        //如果添加数据缺少，则返回
        if(comImg.getComId()==null)return CommonResult.failed("未输入商品编号");
        if(comImg.getByWho()==null)return CommonResult.failed("未输入用户编号");

        // System.out.println(comImg.toString());
        //添加
        comImgMapper.insert(comImg);
        return CommonResult.success("成功添加一张商品图片");
    }

    @Override
    public CommonResult deleteImg(Long ComImgId,Long userId) {
        //如果删除数据缺少，则返回
        if(ComImgId==null)return CommonResult.failed("未输入商品图片编号");
        if(userId==null)return CommonResult.failed("未输入用户编号");
        //通过商品图片编号查询一张图片(这张图片必须在服务器中存在)
        ComImg comImg = comImgMapper.selectById(ComImgId);

        if(comImg == null) return CommonResult.failed("不存在此图片");

        //如果数据库中保存了图片地址（但是并没有判断格式），且如果保存到了服务器的图片设置了init_img_address长度绝对大于30
        if(comImg.getComImgAddress() != null
                && comImg.getComImgGroup() != null
                && comImg.getComImgAddress().length() > 30){
            //删除服务器中的图片
        FastDFSUtil.delete(comImg.getComImgGroup(),comImg.getComImgAddress());
        }

        //更新用户编号（直接通过id的方式会改不了用户编号）
        comImg.setByWho(userId);
        int update = comImgMapper.updateById(comImg);

        //删除数据库的数据（逻辑删除）
        int delete = comImgMapper.deleteById(ComImgId);
        if (delete != 1 || update != 1) return CommonResult.failed("未删除此商品图片");
        return CommonResult.success("已删除一张商品图片");
    }

    @Override
    public CommonResult findAllById(Long com_id) {
        //条件构造器
        QueryWrapper<ComImg> wrapper = new QueryWrapper<>();
        //通过商品编号查询所属的全部图片
        wrapper.eq("com_id",com_id);
        //查询出来全部图片
        List<ComImg> comImgs = comImgMapper.selectList(wrapper);
        //查询是否存在此商品ID
        if(comImgs.isEmpty())return CommonResult.failed("此商品没有图片");

        //最终返回
        List<ComImgVO> comImgVOS = new ArrayList<>();

        //拼接图片地址
        for (ComImg comImg : comImgs) {
            //创建comImg的VO，用于存取拼接的对象（将ComImg的组名与服务器地址拼接）
            ComImgVO comImgVO = new ComImgVO();
            //将ComImg给comImgVO
            comImgVO.setComImgId(comImg.getComImgId());
            comImgVO.setComId(comImg.getComId());
            comImgVO.setAddress(imagesAddress+"/"+comImg.getComImgGroup()+"/"+comImg.getComImgAddress());
            comImgVO.setComImgName(comImg.getComImgName());
            comImgVO.setCreateTime(comImg.getCreateTime());
            comImgVO.setUpdateTime(comImg.getUpdateTime());
            comImgVO.setDeleted(comImg.getDeleted());
            comImgVO.setLastVersion(comImg.getLastVersion());
            comImgVO.setByWho(comImg.getByWho());
            // System.out.println("===========================");
            // System.out.println(comImgVO);
            // System.out.println("===========================");
            comImgVOS.add(comImgVO);
        }
        return CommonResult.success(comImgVOS);
    }

    @Override
    public CommonResult findAllById(Long com_id, Integer currentPage, Integer currentSize) {

        //条件构造器
        QueryWrapper<ComImg> wrapper = new QueryWrapper<>();
        //通过商品编号查询所属的全部图片
        wrapper.eq("com_id",com_id);

        // 参数一：当前页
        // 参数二：页面大小
        Page<ComImg> page = new Page<>(currentPage, currentSize);
        comImgMapper.selectPage(page, wrapper);
        if(page.getRecords().isEmpty()){
            return CommonResult.failed("此商品没有图片");
        }

        //最终返回
        List<ComImgVO> comImgVOS = new ArrayList<>();

        //拼接图片地址
        for (ComImg comImg : page.getRecords()) {
            //创建comImg的VO，用于存取拼接的对象（将ComImg的组名与服务器地址拼接）
            ComImgVO comImgVO = new ComImgVO();
            //将ComImg给comImgVO
            comImgVO.setComImgId(comImg.getComImgId());
            comImgVO.setComId(comImg.getComId());
            comImgVO.setAddress("47.106.183.207/"+comImg.getComImgGroup()+"/"+comImg.getComImgAddress());
            comImgVO.setComImgName(comImg.getComImgName());
            comImgVO.setCreateTime(comImg.getCreateTime());
            comImgVO.setUpdateTime(comImg.getUpdateTime());
            comImgVO.setDeleted(comImg.getDeleted());
            comImgVO.setLastVersion(comImg.getLastVersion());
            comImgVO.setByWho(comImg.getByWho());
            comImgVOS.add(comImgVO);
        }
        return CommonResult.success(comImgVOS);
    }

}
