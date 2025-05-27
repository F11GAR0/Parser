import React, { useState } from 'react';
import {Title, Button, TextInput} from '@patternfly/react-core';
import {Table, Thead, Tr, Th, Tbody, Td} from '@patternfly/react-table';

import axios from 'axios'

import "./style/application.css";
import "@patternfly/react-core/dist/styles/base.css";

function App() {

    const [textInputValue, setTextInputValue] = useState('')

  const handleClick = (value) => {
  axios.post('http://backend/parse', data=JSON.stringify({ "url_encoded": "test" }))
      .then(response => {
      console.log('Success:', response.data);
      })
      .catch(error => {
      console.error('Error:', error);
      })
      .finally(result => {
        console.log(value);
      });
  };

  return (
    <div>
      <div className="application-container">
        <Title headingLevel="h1"> Parser </Title>
        <div className="parse-row">
            <Title headingLevel="h4"> Input URL: </Title>
            <div className="text-input-container">
                <TextInput value={textInputValue} type="text" onChange={(_event, value) => setTextInputValue(value)} aria-label="text input example" />
            </div>
            <Button variant="primary" onClick={() => { handleClick(textInputValue) }}>
                Parse
            </Button>
        </div>
        <div className="results-container">
            <Table aria-label="Selectable table">
                <Thead>
                    <Tr>
                        <Th key="Услуга">Услуга</Th>
                        <Th key="Стоимость">Стоимость</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    <Tr>
                        <Td dataLabel="Услуга">Уборка "Стандарт"</Td>
                        <Td dataLabel="Стоимость">150 руб./кв.м</Td>
                    </Tr>
                    <Tr>
                        <Td dataLabel="Услуга">Генеральная уборка</Td>
                        <Td dataLabel="Стоимость">300 руб./кв.м</Td>
                    </Tr>
                    <Tr>
                        <Td dataLabel="Услуга">Уборка в новостройке/уборка после ремонта, строительства</Td>
                        <Td dataLabel="Стоимость">200 руб./кв.м</Td>
                    </Tr>
                </Tbody>
                </Table>
        </div>
      </div>
    </div>
  );
}

export default App;