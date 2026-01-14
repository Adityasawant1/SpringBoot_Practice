import React from 'react'

const Header = ({toggleModel,noOfContact}) => {
  return (
    <header className='header'>
        <div className='container'>

          <h3>Contact List({noOfContact})</h3>
          <button onClick={toggleModel(true)} className='btn '>
            <i className='bi bi-plus-quare'></i>Add new Contact
          </button>
        </div>
      
    </header>
  )
}

export default Header
