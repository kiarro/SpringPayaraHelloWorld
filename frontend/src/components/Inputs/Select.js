import React from 'react';

function Select(props) {
    const options = props.options.map((text, index) => {
        return <option key={text} value={text}>{text}</option>
    })

    return (
        <div className='InputConteiner'>
            <label className='LabelFor'>
                {props.name}
            </label>
            <select className='IdFor Select' value={props.value} onChange={props.onChange}>
                {options}
            </select>
        </div>
    );
}

export default Select;