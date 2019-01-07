package com.yltx.modulewd.borrow.lsit;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class MySection extends SectionEntity<Record> {
    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(Record record) {
        super(record);
    }
}
