export const sidebarItems = [
    {
        id: 1,
        href: "/admin",
        iconClass: "text-20 icon-discovery",
        text: "Dashboard",
        activeWhen: "/admin",
    },
    {
        id: 2,
        href: "/admin/categories",
        iconClass: "text-20 icon-list",
        text: "Kategori Kursus",
        activeWhen: "/admin/categories/*",
    },
    {
        id: 3,
        href: "/admin/finances/withdrawal",
        iconClass: "text-20 icon-list",
        text: "Withdrawal",
        activeWhen: "/admin/finances/withdrawal/*",
    },
];