package org.motechproject.mds.web.controller;

import org.junit.Test;
import org.motechproject.mds.BaseIT;
import org.motechproject.mds.dto.TypeDto;
import org.motechproject.mds.service.TypeService;
import org.motechproject.mds.web.SelectData;
import org.motechproject.mds.web.SelectResult;
import org.motechproject.mds.web.comparator.TypeDisplayNameComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvailableControllerIT extends BaseIT {

    @Resource(name = "mdsMessageSource")
    private MessageSource messageSource;

    @Autowired
    private TypeService typeService;

    @Autowired
    private AvailableController controller;

    @Test
    public void shouldReturnAllTypes() throws Exception {
        List<TypeDto> expected = typeService.getAllTypes();

        Collections.sort(expected, new TypeDisplayNameComparator(messageSource));

        SelectData data = new SelectData("", 1, expected.size());
        SelectResult<TypeDto> result = controller.getTypes(data);
        assertEquals(expected, result.getResults());
    }

}
