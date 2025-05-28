import React, { useState, useEffect } from 'react';
import {Title, Button, TextInput, Skeleton} from '@patternfly/react-core';
import {Table, Thead, Tr, Th, Tbody, Td} from '@patternfly/react-table';

import axios from 'axios'

import "./style/application.css";
import "@patternfly/react-core/dist/styles/base.css";

function App() {
    const [textInputValue, setTextInputValue] = useState('');
    const [responseData, setResponseData] = useState([]);
    const [loading, setLoading] = useState(false);

    const handleClick = () => {
        axios.post(`http://${window.location.hostname}:8080/api/parser`, { urlEncoded: encodeURI(textInputValue) })
            .then(response => {
                console.log('Success:', response.data);
                setResponseData(response.data);
            })
            .catch(error => {
                console.error('Error:', error);
                setResponseData([]);
            })
            .finally(result => {
                setLoading(false);
            });
    };

    useEffect(() => {
        document.title = "Cleaning Parser"
     }, []);

    return (
        <div>
            <div className="application-container">
                <Title headingLevel="h1"> Parser </Title>
                <div className="parse-row">
                    <Title headingLevel="h4"> Input URL: </Title>
                    <div className="text-input-container">
                        <TextInput value={textInputValue} type="text" onChange={(_event, value) => { setTextInputValue(value); } } aria-label="text input example" />
                    </div>
                    <Button variant="primary" onClick={ () => {handleClick(); setLoading(true);}}>
                        Parse
                    </Button>
                </div>
                <div className="results-container">
                    {loading ? <Skeleton /> : (
                        <Table aria-label="Parser results table">
                        <Thead>
                            <Tr>
                                <Th key="service">Услуга</Th>
                                <Th key="price">Цена</Th>
                            </Tr>
                        </Thead>
                        <Tbody>
                            {responseData.map((row, index) => (
                                <Tr key={index}>
                                    <Td dataLabel="service">{row.serviceInfo}</Td>
                                    <Td dataLabel="price">{row.price}</Td>
                                </Tr>
                            ))}
                        </Tbody>
                    </Table>
                    )}                    
                </div>
            </div>
        </div>
    );
}

export default App;