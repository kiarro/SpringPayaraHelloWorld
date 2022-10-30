import React from 'react';
import './AddDragon.css';
import HeaderButton from './../../components/Buttons/HeaderButton';
import BaseInput from './../../components/Inputs/BaseInput';
import Select from './../../components/Inputs/Select';
import ErrorMessage from '../../components/ErrorMessage/ErrorMessage';

import { useNavigate } from 'react-router-dom';
import {
  useEffect,
  useState
} from 'react'

function AddDragon() {
  const history = useNavigate();
  
  const [error, setError] = useState(null);

  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [weight, setWeight] = useState("");
  const [character, setCharacter] = useState("CUNNING");
  const [type, setType] = useState("WATER");
  const [x, setX] = useState("");
  const [y, setY] = useState("");
  const [depth, setDepth] = useState("");
  const [numberOfTreasures, setNumberOfTreasures] = useState("");

  function sendClick() {
    try {
      var body = {
        name: name,
        coordinates: {
          x: parseFloat(x),
          y: parseFloat(y)
        },
        age: parseInt(age),
        weight: parseInt(weight),
        type: type,
        character: character,
        cave: {
          depth: parseFloat(depth),
          numberOfTreasures: parseFloat(numberOfTreasures)
        }
      };

      fetch("/dragonscaves", {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
      })
      .then(res => {
        if (res.ok) {
          history("/");
        } else {
          setError(res.status);
        }
      });

    } catch (err) {
      setError("Произошла ошибка при формировании запроса. Введены неверные данные.");
    } finally {
    }
  }

  return (
    <main>
      <div className="form">
        <div className='InputConteiner'>
          <BaseInput name="Имя" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <BaseInput name="Возраст" value={age} onChange={(e) => setAge(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <BaseInput name="Вес" value={weight} onChange={(e) => setWeight(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <Select name="Характер" value={character} onChange={(e) => setCharacter(e.target.value)} options={["CUNNING", "WISE", "CHAOTIC", "CHAOTIC_EVIL", "FICKLE"]}></Select>
        </div>
        <div className='InputConteiner'>
        <Select name="Тип" value={type} onChange={(e) => setType(e.target.value)} options={["WATER", "UNDERGROUND", "AIR", "FIRE"]}></Select>
        </div>
        <div className='InputConteiner'>
          <BaseInput name="Х" value={x} onChange={(e) => setX(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <BaseInput name="У" value={y} onChange={(e) => setY(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <BaseInput name="Глубина пещеры" value={depth} onChange={(e) => setDepth(e.target.value)} />
        </div>
        <div className='InputConteiner'>
          <BaseInput name="Количество сокровищ" value={numberOfTreasures} onChange={(e) => setNumberOfTreasures(e.target.value)} />
        </div>
        <ErrorMessage text={error} />
        <button className="AddButton" onClick={sendClick} >Добавить дракона</button>
      </div>
    </main>
  );
}

export default AddDragon;