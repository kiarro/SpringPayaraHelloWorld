import React from 'react';
import './ErrorMessage.css';

function ErrorMessage(props) {
    let text = props.text;
    return <div className='centered error' hidden={!text}>Ошибка: {text}</div>;
    
  }
  
export default ErrorMessage;