import './ReturnCount.css'
import React from 'react';

function ReturnCount(props)
{
    return(
    <main>
        <span>Количество драконов, {props.type} равно: {props.count}</span>
    </main>
    );
}

export default ReturnCount;