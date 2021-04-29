package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.SingleCharacterManageForm;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleCharacterManageMapper {
    Integer allAmount();

    SingleCharacterModel selectByCharacter(String character);

    Integer updateCharacter(SingleCharacterManageForm singleCharacterManageForm);

    Integer deleteCharacter(String character);
}