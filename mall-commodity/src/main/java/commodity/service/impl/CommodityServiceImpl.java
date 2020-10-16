package commodity.service.impl;

import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import commodity.entity.ComSort;
import commodity.entity.Commodity;
import commodity.entity.CommodityVO;
import commodity.entity.ItemParam;
import commodity.mapper.ComSortMapper;
import commodity.mapper.CommodityMapper;
import commodity.mapper.ItemParamMapper;
import commodity.service.ComSortService;
import commodity.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import commodity.service.ItemParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.FastDFSUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    // @Autowired
    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private ComSortMapper comSortMapper;

    @Resource
    private ItemParamMapper itemParamMapper;

    @Override
    public CommonResult findCommodityById(Long com_id) {
        if(com_id == null)return CommonResult.failed("商品编号不可为空");
        //查询商品信息
        Commodity commodity = commodityMapper.selectById(com_id);

        //查询类别信息（类别编号从商品信息获取）
        ComSort comSort = comSortMapper.selectById(commodity.getComSortId());

        //查询参数信息
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id",com_id);
        ItemParam itemParam = itemParamMapper.selectOne(wrapper);

        //将需要的信息存到商品VO
        CommodityVO commodityVO = new CommodityVO();
        commodityVO.setComId(com_id);
        commodityVO.setComSortId(commodity.getComSortId());
        commodityVO.setComTypeName(comSort.getComTypeName());
        commodityVO.setComName(commodity.getComName());
        commodityVO.setComPrice(commodity.getComPrice());
        commodityVO.setComStock(commodity.getComStock());
        commodityVO.setComStatus(commodity.getComStatus());
        commodityVO.setMainImage(commodity.getMainImage());
        commodityVO.setItemParamData(itemParam.getItemParamData());
        commodityVO.setCreateTime(commodity.getCreateTime());
        commodityVO.setUpdateTime(commodity.getUpdateTime());
        commodityVO.setDeleted(commodity.getDeleted());
        commodityVO.setLastVersion(commodity.getLastVersion());
        commodityVO.setByWho(commodity.getByWho());

        //返回
        return CommonResult.success(commodityVO);
    }

    @Override
    public CommonResult listCommodity() {
        List<Commodity> commodities = commodityMapper.selectList(null);
        if(commodities.isEmpty()){
            return CommonResult.failed("没有任何商品信息");
        }
        return CommonResult.success(commodities);
    }

    @Override
    public CommonResult listCommodity(Integer currentPage, Integer currentSize) {
        // 参数一：当前页
        // 参数二：页面大小
        Page<Commodity> page = new Page<>(currentPage, currentSize);
        commodityMapper.selectPage(page, null);
        if(page.getRecords().isEmpty()){
            return CommonResult.failed("没有任何商品信息");
        }

        Map<String, Object> map = new HashMap<>();
        //查询数据
        map.put("commodityRecords",page.getRecords());
        //总数据量
        map.put("commodityTotal",page.getTotal());
        return CommonResult.success(map);
    }

    @Transactional
    @Override
    public CommonResult addCommodity(Commodity commodity) {
        /*
        商品编号    自动生成
        商品类型编号  输入-从商品分类获取
        商品名称    输入
        商品价格     输入
        商品库存    输入
        商品状态(O为下架，1为销售) 默认1
        主要的图片（首页显示)     controller拼接
        创建时间    自动生成
        更新时间    自动生成
        本记录是否有效 自动生成
        历史版本    自动生成
        操作者     输入-用户编号
        */
        if(commodity.getComSortId()==null)return CommonResult.failed("商品分类编号不能为空");
        if(commodity.getComName()==null)return CommonResult.failed("商品名称不能为空");
        if(commodity.getComPrice()==null)return CommonResult.failed("商品价格不能为空");
        if(commodity.getComStock()==null)return CommonResult.failed("商品库存不能为空");
        // if(commodity.getMainImage()==null)return CommonResult.failed("未上传商品主要图片");
        if(commodity.getByWho()==null)return CommonResult.failed("用户编号不能为空");
        if(commodity.getComPrice() < 0)return CommonResult.failed("商品价格不能小于0");
        if(commodity.getComStock() < 0)return CommonResult.failed("商品库存不能小于0");
        //查询数据库是否存在相同商品（商品分类编号与商品名称一致）
        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();
        wrapper.eq("com_sort_id",commodity.getComSortId());
        wrapper.eq("com_name",commodity.getComName());
        Commodity one = commodityMapper.selectOne(wrapper);
        if(one != null)return CommonResult.failed("已存在此商品");

        // 否则 添加商品
        int insert = commodityMapper.insert(commodity);
        if(insert != 1)return CommonResult.failed("添加失败");

        return  CommonResult.success("添加商品成功");
    }

    @Transactional
    @Override
    public CommonResult updateCommodity(Commodity commodity) {
         /*
        商品编号    必须输入（从已查询的商品信息获取）
        商品类型编号  输入-从商品分类获取（可修改）
        商品名称    输入（可修改）
        商品价格     输入（可修改）
        商品库存    输入（可修改）
        商品状态(O为下架，1为销售) 默认1（可修改）
        历史版本    自动生成
        操作者     输入-用户编号（可修改）
        */
         //必要字段不可为空
         if(commodity.getComId()==null)return CommonResult.failed("商品编号不可为空");
         if(commodity.getByWho()==null)return CommonResult.failed("用户编号不可为空");
        // 更新
        int update = commodityMapper.updateById(commodity);
        //更新是否成功
        if(update != 1)return CommonResult.failed("修改商品失败");
        return CommonResult.success("修改商品成功");
    }

    @Transactional
    @Override
    public CommonResult updateCommodity(Long com_id, Integer com_num, Long userId) {
        //必要字段不可为空
        if(com_id==null)return CommonResult.failed("商品编号不可为空");
        if(com_num==null || com_num < 1)return CommonResult.failed("用户购买数量必须大于0");
        if(userId==null)return CommonResult.failed("用户编号不可为空");
        //查询是否存在
        Commodity commodity = commodityMapper.selectById(com_id);
        if(commodity==null)CommonResult.failed("不存在此商品");
        if(commodity.getComStock().intValue() < com_num)CommonResult.failed("商品数量不足");
        //设置
        commodity.setComStock(commodity.getComStock() - com_num);
        commodity.setByWho(userId);

        int update = commodityMapper.updateById(commodity);
        if(update != 1)return CommonResult.failed("未减少商品数量");
        return CommonResult.success("已减少商品数量");
    }

    @Transactional
    @Override
    public CommonResult updateMainImg(Long com_id, String mainImg, Long userId) {
        //必要字段不可为空
        if(com_id==null)return CommonResult.failed("商品编号不可为空");
        if(mainImg==null)return CommonResult.failed("没有上传图片");
        if(userId==null)return CommonResult.failed("用户编号不可为空");
        //查询是否存在
        Commodity commodity = commodityMapper.selectById(com_id);
        if(commodity==null)CommonResult.failed("不存在此商品");

        //如果数据库商品主图片不存在，则不用删除服务器图片
        if(commodity.getMainImage()!=null){
        //获取原来服务器图片的地址并删除
        String groupName = commodity.getMainImage().substring(0,commodity.getMainImage().indexOf("/"));

        String addressName = commodity.getMainImage().substring(
                commodity.getMainImage().lastIndexOf("/"), commodity.getMainImage().length());
        //删除服务器中的图片
        FastDFSUtil.delete(groupName,addressName);
        }

        //设置
        commodity.setMainImage(mainImg);
        commodity.setByWho(userId);
        int update = commodityMapper.updateById(commodity);
        if(update != 1)return CommonResult.failed("修改图片失败");
        return CommonResult.success("修改图片成功");
    }

    @Transactional
    @Override
    public CommonResult deleteCommodity(Long com_id, Long userId) {
        //必要字段不可为空
        if(com_id==null)return CommonResult.failed("商品编号不可为空");
        if(userId==null)return CommonResult.failed("用户编号不可为空");

        //查询是否存在
        Commodity commodity = commodityMapper.selectById(com_id);
        if(commodity==null)CommonResult.failed("不存在此商品");
        commodity.setByWho(userId);
        //删除
        int delete = commodityMapper.deleteById(com_id);
        if(delete != 1)return CommonResult.failed("未删除此商品");
        return CommonResult.success("已删除此商品");
    }


}
