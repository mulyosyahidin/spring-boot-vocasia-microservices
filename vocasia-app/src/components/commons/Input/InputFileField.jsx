export const InputFileField = ({
                                   label,
                                   name,
                                   value,
                                   onChange,
                                   error,
                                   isRequired = false,
                                   hint = null
                               }) => {
    return (
        <>
            <label className="text-16 lh-1 fw-500 text-dark-1 mb-10">{label}</label>
            <input
                type="file"
                name={name}
                onChange={onChange}
                required={isRequired}
            />

            {hint && <small className="text-dark-1">{hint}</small>}

            {error && (
                <div className={'mt-2'}>
                    <small className="text-red-1">{error}</small>
                </div>
            )}
        </>
    );
};
