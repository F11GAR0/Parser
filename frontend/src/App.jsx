import React from 'react';
import TextInputBasic from './components/TextInput';
import {Title, Button} from '@patternfly/react-core';
import {Table, Thead, Tr, Th, Tbody, Td} from '@patternfly/react-table';

import "./style/application.css";
import "@patternfly/react-core/dist/styles/base.css";

function App() {
  return (
    <div>
      <div className="application-container">
        <Title headingLevel="h1"> Parser </Title>
        <div className="parse-row">
            <Title headingLevel="h4"> Input URL: </Title>
            <TextInputBasic/>
            <Button variant="primary" size="sm">
                Parse
            </Button>
        </div>
        <div className="results-container">
            <Table aria-label="Selectable table">
                <Thead>
                    <Tr>
                        <Th key="ID">Identificator</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    <Tr>
                        <Td dataLabel="ID">001</Td>
                    </Tr>
                </Tbody>
                </Table>
        </div>
      </div>
    </div>
  );
}

export default App;