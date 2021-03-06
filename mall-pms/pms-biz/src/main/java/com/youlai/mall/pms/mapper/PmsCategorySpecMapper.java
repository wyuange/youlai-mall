package com.youlai.mall.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.mall.pms.pojo.PmsCategorySpec;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PmsCategorySpecMapper extends BaseMapper<PmsCategorySpec> {

    @Select("<script>" +
            " SELECT " +
            "    t1.id,t1.category_id,t1.name,t2.id AS spuId " +
            " FROM " +
            "    pms_spec_category t1 " +
            " LEFT JOIN pms_spu t2 ON t1.category_id = t2.category_id " +
            "    WHERE " +
            "   t2.id =#{spuId} " +
            "</script>")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "values", column = "{specCategoryId= t1.id,spuId=spuId}", many = @Many(select = "com.youlai.mall.pms.mapper.PmsSpecValueMapper.listBySpuIdAndSpecId"))
    })
     List<PmsCategorySpec> listBySpuId(Long spuId);
}
