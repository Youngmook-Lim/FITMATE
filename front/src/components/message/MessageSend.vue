<template>
  <div>
    <h2>메세지 보내기</h2>
    <div class="autosuggest-container">
      <span>To </span>
      <vue-autosuggest
        id="search-user"
        v-model="query"
        :suggestions="filteredOptions"
        @click="clickHandler"
        @input="onInputChange"
        @selected="onSelected"
        :get-suggestion-value="getSuggestionValue"
        :input-props="{
          id: 'autosuggest__input',
          placeholder: '닉네임을 검색하세요',
        }"
        :limit="10"
      >
        <div
          slot-scope="{ suggestion }"
          style="display: flex; align-items: center"
        >
          <div style="{ display: 'flex', color: 'navyblue'}">
            {{ suggestion.item }}
          </div>
        </div>
      </vue-autosuggest>
    </div>
    <div>
      <!-- <label for="msg-content">Content</label> -->
      <p>Content</p>
      <textarea
        v-model="message"
        id="msg-content"
        cols="50"
        rows="20"
      ></textarea>
    </div>
    <button @click="sendMessage">SEND</button>
  </div>
</template>

<script>
import { VueAutosuggest } from "vue-autosuggest";
import axios from "@/util/http-common.js";

export default {
  name: "MessageSend",
  components: {
    VueAutosuggest,
  },

  data() {
    return {
      message: "",
      query: "",
      selected: "",
      suggestions: [
        {
          data: [
            "Frodo",
            "Samwise",
            "Gandalf",
            "Galadriel",
            "Faramir",
            "Éowyn",
            "Peregrine Took",
            "Boromir",
            "Legolas",
            "Gimli",
            "Gollum",
            "Beren",
            "Saruman",
            "Sauron",
            "Théoden",
          ],
        },
      ],
    };
  },
  computed: {
    filteredOptions() {
      if (!this.query) return [{ data: [] }];
      return [
        {
          data: this.suggestions[0].data
            .filter((option) => {
              return (
                option.toLowerCase().indexOf(this.query.toLowerCase()) > -1
              );
            })
            .slice(0, this.limit),
        },
      ];
    },
  },
  methods: {
    clickHandler(item) {
      console.log(item);
    },
    onSelected(item) {
      this.selected = item.item;
      console.log(this.selected);
    },
    onInputChange(text) {
      console.log(text);
    },
    getSuggestionValue(suggestion) {
      return suggestion.item;
    },
    sendMessage() {
      axios({
        url: "messageApi/",
        method: "POST",
        params: {
          // to_user, from_user 왜없지???
          from_user: this.$store.state.myUser.nickname,
          to_user: this.selected,
          message: this.message,
        },
      }).then((res) => {
        console.log(res.data);
      });
    },
  },
  created() {
    const id = this.$route.params.id;
    console.log(id);
    if (id == 0) return;
    axios
      .get(`userApi/detail`, { params: { id: id } })
      .then((res) => (this.query = res.data.nickname));
  },
};
</script>

<style>
.autosuggest-container input {
  width: 260px;
  padding: 0.5rem;
}

.autosuggest-container ul {
  width: 100%;
  color: rgba(30, 39, 46, 1);
  list-style: none;
  margin: 0;
  padding: 0.5rem 0 0.5rem 0;
  border: 1px solid black;
  border-radius: 0 0 9px 9px;
}

.autosuggest-container li {
  margin: 0 0 0 0;
  border-radius: 5px;
  padding: 0.75rem 0 0.75rem 0.75rem;
  display: flex;
  align-items: center;
}

.autosuggest-container li:hover {
  cursor: pointer;
}

.autosuggest-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
  width: 280px;
  margin: 0 auto;
}

#autosuggest {
  width: 100%;
  display: block;
}

.autosuggest__results-item--highlighted {
  background-color: rgba(51, 217, 178, 0.2);
}
</style>
