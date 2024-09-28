export const organizeCategories = (categories) => {
    let organizedCategories = [];

    categories.forEach(category => {
        organizedCategories.push({
            value: category.id,
            label: category.name,
        });

        let children = category.children;
        if (children.length > 0) {
            children.forEach(child => {
                organizedCategories.push({
                    value: child.id,
                    label: `${category.name} > ${child.name}`
                });
            });
        }
    });

    return organizedCategories
};