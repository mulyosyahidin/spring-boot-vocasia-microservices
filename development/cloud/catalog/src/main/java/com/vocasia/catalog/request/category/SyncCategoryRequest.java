package com.vocasia.catalog.request.category;

import com.vocasia.catalog.dto.data.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SyncCategoryRequest {

    private List<CategoryDto> categories;

}
