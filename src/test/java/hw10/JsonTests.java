package hw10;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw10.model.EllipseModel;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;


public class JsonTests {

    @Test
    void jsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/test/resources/jsonExample.json");
        EllipseModel ellipseModel = objectMapper.readValue(jsonFile, EllipseModel.class);

        assertThat(ellipseModel.getObjectType()).isEqualTo("ellipse");
        assertThat(ellipseModel.getId()).isEqualTo("ee8be380-0ba3-e876-ec5f-412cd6442034");
        assertThat(ellipseModel.getCoords().get(0).getLatitude()).isEqualTo(53.70321053273598);
        assertThat(ellipseModel.getCoords().get(0).getLongitude()).isEqualTo(35.9033203125);
        assertThat(ellipseModel.getTags().getCreator()).isEqualTo("default-creator");
        assertThat(ellipseModel.getTags().isDraggable()).isTrue();
    }
}
