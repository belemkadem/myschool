package com.lastgeneration.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lastgeneration.product.web.rest.TestUtil;

public class GradeClassTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GradeClass.class);
        GradeClass gradeClass1 = new GradeClass();
        gradeClass1.setId(1L);
        GradeClass gradeClass2 = new GradeClass();
        gradeClass2.setId(gradeClass1.getId());
        assertThat(gradeClass1).isEqualTo(gradeClass2);
        gradeClass2.setId(2L);
        assertThat(gradeClass1).isNotEqualTo(gradeClass2);
        gradeClass1.setId(null);
        assertThat(gradeClass1).isNotEqualTo(gradeClass2);
    }
}
