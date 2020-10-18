package commodity.service.impl;

import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.discovery.converters.Auto;
import commodity.entity.Commodity;
import commodity.entity.ItemParam;
import commodity.mapper.ItemParamMapper;
import commodity.service.ItemParamService;
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
 * 商品详细信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {

    // @Autowired
    @Resource
    private ItemParamMapper itemParamMapper;

    @Override
    public CommonResult<ItemParam> findItemParamById(Long com_id) {

        //通过传过来的商品参数查询这条参数信息
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id",com_id);
        ItemParam one = itemParamMapper.selectOne(wrapper);

        //查询不到返回信息
        if(one == null){
            return CommonResult.failed("没有此商品参数信息");
        }
        //查询到则返回数据（主要的数据为json字符串）
        return CommonResult.success(one);
    }

    @Override
    public CommonResult findItemParam() {
        List<ItemParam> itemParams = itemParamMapper.selectList(null);
        if(itemParams.isEmpty()){
            return CommonResult.failed("没有任何商品参数信息");
        }
        return CommonResult.success(itemParams);
    }

    @Override
    public CommonResult findItemParam(Integer currentPage, Integer currentSize) {
        // 参数一：当前页
        // 参数二：页面大小
        Page<ItemParam> page = new Page<>(currentPage, currentSize);
        itemParamMapper.selectPage(page, null);
        if(page.getRecords().isEmpty()){
            return CommonResult.failed("没有任何商品参数信息");
        }

        Map<String, Object> map = new HashMap<>();
        //查询数据
        map.put("itemParamRecords",page.getRecords());
        //总数据量
        map.put("itemParamTotal",page.getTotal());
        return CommonResult.success(map);
    }


    @Transactional
    @Override
    public CommonResult addItemParam(ItemParam itemParam) {
        if(itemParam.getByWho() == null)return CommonResult.failed("用户编号不能为空");
        if(itemParam.getComId() == null)return CommonResult.failed("商品编号不能为空");
        if(itemParam.getItemParamData() == null)return CommonResult.failed("商品参数数据不能为空");

        //通过传过来的商品参数查询这条参数信息
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id",itemParam.getComId());
        ItemParam one = itemParamMapper.selectOne(wrapper);
        //查询不到返回信息
        if(one != null){
            return CommonResult.failed("已存在商品参数信息");
        }

        //设置商品参数信息
        ItemParam param = new ItemParam();
        param.setComId(itemParam.getComId());
        param.setItemParamData(itemParam.getItemParamData());
        param.setByWho(itemParam.getByWho());
        //添加到数据库
        itemParamMapper.insert(param);
        return CommonResult.success("添加商品参数成功");
    }


    @Transactional
    @Override
    public CommonResult updateItemParam(ItemParam itemParam) {
        if(itemParam.getByWho() == null)return CommonResult.failed("用户编号不能为空");
        if(itemParam.getComId() == null)return CommonResult.failed("商品编号不能为空");
        if(itemParam.getItemParamData() == null)return CommonResult.failed("商品参数数据不能为空");
        //通过传过来的商品参数查询这条参数信息
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id",itemParam.getComId());
        ItemParam one = itemParamMapper.selectOne(wrapper);

        //查询不到返回信息
        if(one == null){
            return CommonResult.failed("没有此商品参数信息");
        }

        //设置商品参数信息
        ItemParam param = new ItemParam();
        //设置通过商品编号查询到的参数编号
        param.setItemParamId(one.getItemParamId());
        //参数数据可修改
        param.setItemParamData(itemParam.getItemParamData());
        //一定要加上这句，否则乐观锁就不起作用
        param.setLastVersion(one.getLastVersion());
        //用户编号可修改
        param.setByWho(itemParam.getByWho());
        //执行更新
        itemParamMapper.updateById(param);
        return CommonResult.success("已更新此商品参数");
    }

    @Transactional
    @Override
    public CommonResult deleteItemParam(Long com_id, Long userId) {
        //通过传过来的商品参数查询这条参数信息
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("com_id",com_id);
        ItemParam one = itemParamMapper.selectOne(wrapper);

        if(one == null){
            return CommonResult.failed("没有此商品参数信息");
        }

        //更新用户编号（直接通过id的方式会改不了用户编号）
        one.setByWho(userId);
        int update = itemParamMapper.updateById(one);

        //mybatis-plus已设置逻辑删除
        int delete = itemParamMapper.deleteById(one.getItemParamId());
        if (delete != 1 || update != 1) return CommonResult.failed("未删除此商品");
        return CommonResult.success("已删除此商品参数");
    }


}
