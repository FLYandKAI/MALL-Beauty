package commodity.service.impl;

import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.discovery.converters.Auto;
import commodity.entity.ComSort;
import commodity.entity.Commodity;
import commodity.entity.ItemParam;
import commodity.mapper.ComSortMapper;
import commodity.service.ComSortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品分类信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@Service
public class ComSortServiceImpl extends ServiceImpl<ComSortMapper, ComSort> implements ComSortService {

    // @Autowired
    @Resource
    private ComSortMapper comSortMapper;

    @Override
    public CommonResult listComSort() {
        List<ComSort> comSorts = comSortMapper.selectList(null);
        if(comSorts.isEmpty()){
            return CommonResult.failed("没有任何商品分类信息");
        }
        return CommonResult.success(comSorts);
    }

    @Override
    public CommonResult listComSort(Integer currentPage, Integer currentSize) {
        // 参数一：当前页
        // 参数二：页面大小
        Page<ComSort> page = new Page<>(currentPage, currentSize);
        comSortMapper.selectPage(page, null);
        if(page.getRecords().isEmpty()){
            return CommonResult.failed("没有任何商品分类信息");
        }
        Map<String, Object> map = new HashMap<>();
        //查询数据
        map.put("comSortRecords",page.getRecords());
        //总数据量
        map.put("comSortTotal",page.getTotal());
        return CommonResult.success(map);
    }

    @Override
    public CommonResult<ComSort> findComSortById(Long com_type_id) {
        //查询
        ComSort comSort = comSortMapper.selectById(com_type_id);
        //如果不存在
        if(comSort == null){
            return CommonResult.failed("没有任何商品分类信息");
        }
        return CommonResult.success(comSort);
    }

    @Transactional
    @Override
    public CommonResult addComSort(ComSort comSort) {

        if(comSort.getComTypeId()==null)return CommonResult.failed("商品类型编号不能为空");
        if(comSort.getComTypeName()==null)return CommonResult.failed("商品分类名称不能为空");
        if(comSort.getComTypeParentId()==null)return CommonResult.failed("商品父类编号不能为空");
        if(comSort.getByWho()==null)return CommonResult.failed("用户编号不能为空");
        //查询
        ComSort cSort = comSortMapper.selectById(comSort.getComTypeId());
        //如果已经存在
        if(cSort != null){
            return CommonResult.failed("已存在此商品分类");
        }

        //条件构造器
        QueryWrapper<ComSort> wrapper = new QueryWrapper<>();
        //查询父类id是不是已存在的商品分类id
        wrapper.eq("com_type_id",comSort.getComTypeParentId());
        ComSort one = comSortMapper.selectOne(wrapper);

        //如果此父类id为不存在，则返回不存在
        if(one == null){
            return CommonResult.failed("请选择已存在的商品分类编号，作为新增商品分类的父类编号");
        }
        //添加
        comSortMapper.insert(comSort);
        return CommonResult.success("添加商品分类成功");
    }

    @Transactional
    @Override
    public CommonResult updateComSort(ComSort comSort) {
        if(comSort.getComTypeId()==null)return CommonResult.failed("商品类型编号不能为空");
        if(comSort.getByWho()==null)return CommonResult.failed("用户编号不能为空");
        if(comSort.getComTypeName()==null && comSort.getComTypeParentId() == null&& comSort.getComTypeStatus() ==null)
            return CommonResult.failed("未作任何修改");
        //查询
        ComSort cSort = comSortMapper.selectById(comSort.getComTypeId());
        //如果已经存在
        if(cSort == null){
            return CommonResult.failed("不存在此商品分类");
        }

        //条件构造器
        QueryWrapper<ComSort> wrapper = new QueryWrapper<>();
        //查询父类id是不是已存在的商品分类id
        wrapper.eq("com_type_id",comSort.getComTypeParentId());
        ComSort one = comSortMapper.selectOne(wrapper);

        //就是父类ID并不是从已有的分类ID中选择的，则返回不存在
        if(comSort.getComTypeParentId()!=null && one == null && comSort.getComTypeParentId() != 0L){
            return CommonResult.failed("请选择已存在的商品分类编号，作为修改商品分类的父类编号");
        }
        //乐观锁必要
        comSort.setLastVersion(one.getLastVersion());

        comSortMapper.updateById(comSort);
        return CommonResult.success("修改商品分类成功");
    }

    @Transactional
    @Override
    public CommonResult deleteComSort(Long com_type_id, Long userId) {
        //查询
        ComSort comSort = comSortMapper.selectById(com_type_id);
        //查询不到返回信息
        if(comSort == null){
            return CommonResult.failed("没有此商品分类信息");
        }

        //条件构造器
        QueryWrapper<ComSort> wrapper = new QueryWrapper<>();
        //查询父类id是不是已存在的商品分类id
        wrapper.eq("com_type_parent_id",com_type_id);
        List<ComSort> comSortList = comSortMapper.selectList(wrapper);

        //如果此父类已存在
        if(!comSortList.isEmpty()){
            //查询其下所有商品
            // List<ComSort> listComSorts = comSortMapper.selectList(wrapper);
            //删除其下所有商品
            // comSortMapper.deleteBatchIds(listComSorts);
            return CommonResult.failed("此商品分类为父类商品，不可被删除");
        }

        //mybatis-plus已设置逻辑删除
        comSortMapper.deleteById(com_type_id);
        return CommonResult.success("已删除此商品分类");
    }


}
