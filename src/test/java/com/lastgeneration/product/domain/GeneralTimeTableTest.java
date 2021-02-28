package com.lastgeneration.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lastgeneration.product.web.rest.TestUtil;

public class GeneralTimeTableTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeneralTimeTable.class);
        GeneralTimeTable generalTimeTable1 = new GeneralTimeTable();
        generalTimeTable1.setId(1L);
        GeneralTimeTable generalTimeTable2 = new GeneralTimeTable();
        generalTimeTable2.setId(generalTimeTable1.getId());
        assertThat(generalTimeTable1).isEqualTo(generalTimeTable2);
        generalTimeTable2.setId(2L);
        assertThat(generalTimeTable1).isNotEqualTo(generalTimeTable2);
        generalTimeTable1.setId(null);
        assertThat(generalTimeTable1).isNotEqualTo(generalTimeTable2);
    }
}
