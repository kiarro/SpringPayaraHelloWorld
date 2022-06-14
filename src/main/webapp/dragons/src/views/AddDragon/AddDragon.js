import React from 'react';
import './AddDragon.css';
import HeaderButton from './../../components/Buttons/HeaderButton';
import BaseInput from './../../components/Inputs/BaseInput';

function AddDragon()
{
    return(
  <main>
  <form>
   <div className='InputConteiner'> 
    <BaseInput name="Имя" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Возраст" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Вес" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Характер" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Тип" enter=""/>
   </div>
   <div className='InputConteiner'>
    <BaseInput name="Х" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="У" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Глубина пещеры" enter=""/>
   </div>
   <div className='InputConteiner'> 
    <BaseInput name="Количество сокровищ" enter=""/>
   </div>
   <HeaderButton name="Добавить дракона" enter=""/>
  </form>
  </main>
    );
}

export default AddDragon;