export const sidebarItems = [
    {
        id: 1,
        href: "/users",
        iconClass: "text-20 icon-discovery",
        text: "Dashboard",
        activeWhen: "/users",
    },
    {
        id: 2,
        href: "/users/courses",
        iconClass: "text-20 icon-play-button",
        text: "Kursus Saya",
        activeWhen: "/users/courses/*",
    },
    {
        id: 3,
        href: "/instructor/transactions",
        iconClass: "text-20 icon-play-button",
        text: "Transaksi",
        activeWhen: "/users/transactions/*",
    }
];