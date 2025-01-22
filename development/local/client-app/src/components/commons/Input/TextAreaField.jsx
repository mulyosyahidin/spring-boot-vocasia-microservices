export const TextAreaField = ({label, name, value, onChange, error, placeholder = null, hint = null}) => {
    return (
        <>
            <label className="text-16 lh-1 fw-500 text-dark-1 mb-10">{label}</label>
            <textarea
                required
                name={name}
                rows="8"
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            ></textarea>

            {hint && <small className="text-dark-1">{hint}</small>}

            {error && (
                <div className={'mt-2'}>
                    <small className="text-red-1">{error}</small>
                </div>
            )}
        </>
    );
}