export const SelectField = ({ label, name, options, value, onChange, error, placeholder = null, isRequired = false, hint = null}) => {
    return (
        <>
            {label && <label className="text-16 lh-1 fw-500 text-dark-1 mb-10">{label}</label>}
            <select name={name} value={value} onChange={onChange} required={isRequired} className="input-box">
                {
                    placeholder && <option disabled>{placeholder}</option>
                }
                {options.map(option => (
                    <option key={option.value} value={option.value}>{option.label}</option>
                ))}
            </select>

            {hint && <small className="text-dark-1">{hint}</small>}

            {error && (
                <div className={'mt-2'}>
                    <small className="text-red-1">{error}</small>
                </div>
            )}
        </>
    );
};
