package com.vocasia.course.request.client.catalog;

import com.vocasia.course.dto.data.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SyncCategoryRequest {

    private List<CategoryDto> categories;

}
