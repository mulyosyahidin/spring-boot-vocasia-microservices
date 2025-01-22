export const formatDate = (date) => {
    const jakartaOffset = 7 * 60;
    const localDate = new Date(date.getTime() + jakartaOffset * 60 * 1000);

    return localDate.toISOString().replace('T', ' ').split('.')[0];
}
