import {useState} from 'react';
import {TextInput} from '@patternfly/react-core';
import "@patternfly/react-core/dist/styles/base.css";

export const TextInputBasic = () => {
  const [value, setValue] = useState('');
  return <TextInput value={value} type="text" onChange={(_event, value) => setValue(value)} aria-label="text input example" />;
};

export default TextInputBasic;