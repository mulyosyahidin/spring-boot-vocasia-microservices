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
        href: "/instructor/reports",
        iconClass: "text-20 icon-play-button",
        text: "Laporan",
        activeWhen: "/instructor/reports/*",
    },

];