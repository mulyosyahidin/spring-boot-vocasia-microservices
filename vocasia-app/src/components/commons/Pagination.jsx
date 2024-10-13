export const Pagination = ({ pagination, onPageChange }) => {
    const { per_page, total, current_page } = pagination;

    // Fungsi untuk berpindah ke halaman sebelumnya dan berikutnya
    const handlePrevPage = () => {
        if (current_page > 1) {
            onPageChange(current_page - 1);
        }
    };

    const handleNextPage = () => {
        if (current_page < total) {
            onPageChange(current_page + 1);
        }
    };

    const renderPageNumbers = () => {
        let pages = [];
        for (let i = 1; i <= total; i++) {
            pages.push(
                <a
                    key={i}
                    href="#"
                    className={i === current_page ? '-count-is-active' : ''}
                    onClick={() => onPageChange(i)}
                >
                    {i}
                </a>
            );
        }
        return pages;
    };

    return (
        <div className="pagination -buttons">
            <button
                className="pagination__button -prev"
                onClick={handlePrevPage}
                disabled={current_page === 1}
            >
                <i className="icon icon-chevron-left"></i>
            </button>

            <div className="pagination__count">
                {renderPageNumbers()}
            </div>

            <button
                className="pagination__button -next"
                onClick={handleNextPage}
                disabled={current_page === total}
            >
                <i className="icon icon-chevron-right"></i>
            </button>
        </div>
    );
};
