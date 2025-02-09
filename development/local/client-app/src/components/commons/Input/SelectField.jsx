export const SelectField = ({
                                label,
                                name,
                                options,
                                value,
                                onChange,
                                error,
                                placeholder = null,
                                isRequired = false,
                                selectedId = null,
                                hint = null
                            }) => {
    return (
        <>
            {label && <label className="text-16 lh-1 fw-500 text-dark-1 mb-10">{label}</label>}
            <select name={name} value={value} onChange={onChange} required={isRequired} className="input-box" defaultValue={selectedId}>
                {
                    placeholder && <option>{placeholder}</option>
                }
                {options.map((option, i) => (
                    <option
                        key={`select-option-${option.value}-${i}`}
                        value={option.value}
                    >{option.label}</option>
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
