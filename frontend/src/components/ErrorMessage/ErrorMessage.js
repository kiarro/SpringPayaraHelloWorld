import React from 'react';
import './ErrorMessage.css';

function ErrorMessage(props) {
    let text = props.text;
    return <div className='centered' hidden={!text}>Ошибка: http status code {text}</div>;
    
  }
  
export default ErrorMessage;