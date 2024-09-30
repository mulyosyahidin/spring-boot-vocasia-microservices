export const organizeCategories = (categories, defaultSelectedId = null) => {
    console.log('Selected ID:', defaultSelectedId);

    let organizedCategories = [];

    categories.forEach(category => {
        const isSelected = category.id === defaultSelectedId;

        console.log(`Is ${category.name} with ID ${category.id} selected? ${isSelected}`);

        organizedCategories.push({
            value: category.id,
            label: category.name,
            selected: isSelected,
        });

        let children = category.children || [];
        if (children.length > 0) {
            children.forEach(child => {
                const isChildSelected = child.id === defaultSelectedId;

                console.log(`Is ${child.name} with ID ${child.id} selected? ${isChildSelected}`);

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
