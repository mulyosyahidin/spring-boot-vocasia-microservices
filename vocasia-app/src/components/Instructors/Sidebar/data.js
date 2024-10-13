export const sidebarItems = [
    {
        id: 1,
        href: "/instructor",
        iconClass: "text-20 icon-discovery",
        text: "Dashboard",
        activeWhen: "/instructor",
    },
    {
        id: 2,
        href: "/instructor/courses",
        iconClass: "text-20 icon-play-button",
        text: "Kursus Saya",
        activeWhen: "/instructor/courses/*",
    },
    {
        id: 3,
        href: "/instructor/students",
        iconClass: "text-20 icon-play-button",
        text: "Siswa",
        activeWhen: "/instructor/students/*",
    },
    {
        id: 4,
        href: "/instructor/transactions",
        iconClass: "text-20 icon-play-button",
        text: "Transaksi",
        activeWhen: "/instructor/transactions/*",
    },
    {
        id: 5,
        href: "/instructor/finances",
        iconClass: "text-20 icon-play-button",
        text: "Keuangan",
        activeWhen: "/instructor/finances/*",
    },

];