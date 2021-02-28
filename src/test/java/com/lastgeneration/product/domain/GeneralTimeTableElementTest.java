package com.lastgeneration.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lastgeneration.product.web.rest.TestUtil;

public class GeneralTimeTableElementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeneralTimeTableElement.class);
        GeneralTimeTableElement generalTimeTableElement1 = new GeneralTimeTableElement();
        generalTimeTableElement1.setId(1L);
        GeneralTimeTableElement generalTimeTableElement2 = new GeneralTimeTableElement();
        generalTimeTableElement2.setId(generalTimeTableElement1.getId());
        assertThat(generalTimeTableElement1).isEqualTo(generalTimeTableElement2);
        generalTimeTableElement2.setId(2L);
        assertThat(generalTimeTableElement1).isNotEqualTo(generalTimeTableElement2);
        generalTimeTableElement1.setId(null);
        assertThat(generalTimeTableElement1).isNotEqualTo(generalTimeTableElement2);
    }
}
