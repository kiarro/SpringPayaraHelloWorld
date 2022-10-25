import './ReturnCount.css'
import React from 'react';
import { useNavigate, useSearchParams, useLocation } from 'react-router-dom';

import {
    useEffect,
    useState
  } from 'react'

function ReturnCount(props)
{
    let location = useLocation();
    console.log(location);

    const param = {};
    const [searchParams, setSearchParams] = useSearchParams();
    param["type"] = searchParams.get("type");
    param["character"] = searchParams.get("character");

    useEffect(() => {
        fetch("/dragonscaves"+location.pathname+"?"+props.param+"="+param[props.param])
          .then(res => res.json())
          .then(
            (result) => {
              setIsLoaded(true);
              setCount(result.value);
            },
            (error) => {
              setIsLoaded(true);
              setError(error);
            }
          )
      }, [])

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [count, setCount] = useState(0);

    return(
    <main>
        <div className='Center'>Количество драконов, {props.type} {param[props.param]} равно: {count}</div>
    </main>
    );
}

export default ReturnCount;