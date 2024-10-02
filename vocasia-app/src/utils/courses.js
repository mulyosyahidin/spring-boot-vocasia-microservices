export const organizeCategories = ({categories, defaultSelectedId = null, addNull = false}) => {
    let organizedCategories = [];

    if (addNull) {
        organizedCategories.push({
            value: null,
            label: 'Pilih Kategori',
            selected: false,
        });
    }

    categories.forEach(category => {
        const isSelected = category.id === defaultSelectedId;

        organizedCategories.push({
            value: category.id,
            label: category.name,
            selected: isSelected,
        });

        let children = category.children || [];
        if (children.length > 0) {
            children.forEach(child => {
                const isChildSelected = child.id === defaultSelectedId;

                organizedCategories.push({
                    value: child.id,
                    label: `${category.name} > ${child.name}`,
                    selected: isChildSelected,
                });
            });
        }
    });

    return organizedCategories;
};
