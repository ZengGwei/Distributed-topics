#  Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at

#     com.cat.core.http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from django import forms
from django.forms.widgets import Textarea, HiddenInput

class CreateZNodeForm(forms.Form):
  name = forms.CharField(max_length=64)
  data = forms.CharField(required=False, widget=Textarea)
  sequence = forms.BooleanField(required=False)

class EditZNodeForm(forms.Form):
  data = forms.CharField(required=False, widget=Textarea)
  version = forms.IntegerField(required=False, widget=HiddenInput)
  

