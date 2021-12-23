package com.i0dev.ChunkBusters.config;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.templates.AbstractConfiguration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StorageConfig extends AbstractConfiguration {


    public StorageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

}
