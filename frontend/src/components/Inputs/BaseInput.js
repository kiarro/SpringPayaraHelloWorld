import React from 'react';
import './BaseInput.css';

function BaseInput(props)
{
    return(
        <div className='InputConteiner'> 
        <label className='LabelFor'>{props.name}</label>
        <input type="text" className='IdFor' placeholder={props.enter} value={props.value} onChange={props.onChange} />
       </div>
    );
}

export default BaseInput;