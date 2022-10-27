import './Buttons.css'
import React from 'react';

function HeaderButton(props)
{
    return(
    <button className="HeaderButton" onClick={props.onClick} >{props.name}</button>
    );
}

export default HeaderButton;