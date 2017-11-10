package fr.tnducrocq.ufc.data.entity.fighter;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by tony on 03/11/2017.
 */

public class WeightCategoryConverter implements PropertyConverter<WeightCategory, Integer> {

    private static WeightCategory[] ALL_VALUES = {
            WeightCategory.HEAVYWEIGHT,
            WeightCategory.LIGHT_HEAVYWEIGHT,
            WeightCategory.MIDDLEWEIGHT,
            WeightCategory.WELTERWEIGHT,
            WeightCategory.LIGHTWEIGHT,
            WeightCategory.FEATHERWEIGHT,
            WeightCategory.BANTAMWEIGHT,
            WeightCategory.FLYWEIGHT,
            WeightCategory.WOMEN_FEATHERWEIGHT,
            WeightCategory.WOMEN_BANTAMWEIGHT,
            WeightCategory.WOMEN_STRAWWEIGHT
    };

    @Override
    public WeightCategory convertToEntityProperty(Integer databaseValue) {
        return ALL_VALUES[databaseValue];
    }

    @Override
    public Integer convertToDatabaseValue(WeightCategory entityProperty) {
        for (int i = 0; i <= ALL_VALUES.length; i++) {
            if (entityProperty == ALL_VALUES[i])
                return i;
        }
        return 0;
    }
}
