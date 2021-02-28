package com.lastgeneration.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lastgeneration.product.web.rest.TestUtil;

public class TutorTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TutorType.class);
        TutorType tutorType1 = new TutorType();
        tutorType1.setId(1L);
        TutorType tutorType2 = new TutorType();
        tutorType2.setId(tutorType1.getId());
        assertThat(tutorType1).isEqualTo(tutorType2);
        tutorType2.setId(2L);
        assertThat(tutorType1).isNotEqualTo(tutorType2);
        tutorType1.setId(null);
        assertThat(tutorType1).isNotEqualTo(tutorType2);
    }
}
