package com.agilityio.open_api_demo.common.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Keep singleton of LOOSE ModelMapper instance.
 */
public class LooseModelMapper {
    private static ModelMapper looseModelMapper;

    private LooseModelMapper() {}

    /**
     * Singleton which generate instance
     *
     * @return ModelMapper instance
     */
    public static ModelMapper getInstance() {
        if (looseModelMapper == null) {
            looseModelMapper = new ModelMapper();
            looseModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }

        return looseModelMapper;
    }
}
