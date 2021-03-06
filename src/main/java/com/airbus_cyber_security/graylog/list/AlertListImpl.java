package com.airbus_cyber_security.graylog.list;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.graylog2.database.CollectionName;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;


@AutoValue
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@CollectionName("wizard_lists")
public abstract class AlertListImpl implements AlertList {


    @JsonProperty("title")
    @Override
    @NotNull
    public abstract String getTitle();

    @JsonProperty("created_at")
    @Override
    @Nullable
    public abstract DateTime getCreatedAt();

    @JsonProperty("creator_user_id")
    @Override
    @Nullable
    public abstract String getCreatorUserId();

    @JsonProperty("last_modified")
    @Override
    @Nullable
    public abstract DateTime getLastModified();

    @JsonProperty("description")
    @Override
    @Nullable
    public abstract String getDescription();

    @JsonProperty("usage")
    @Override
    @NotNull
    public abstract int getUsage();

    @JsonProperty("lists")
    @Override
    @Nullable
    public abstract String getLists();

    @JsonCreator
    public static AlertListImpl create(@JsonProperty("_id") String objectId,
                                       @JsonProperty("title") String title,
                                       @JsonProperty("created_at") DateTime createdAt,
                                       @JsonProperty("creator_user_id") String creatorUserId,
                                       @JsonProperty("last_modified") DateTime lastModified,
                                       @JsonProperty("description") String description,
                                       @JsonProperty("usage") int usage,
                                       @JsonProperty("lists") String lists){
        return new AutoValue_AlertListImpl(title, createdAt, creatorUserId,
                lastModified, description, usage, lists);
    }

    public static AlertListImpl create(
            String title,
            DateTime createdAt,
            String creatorUserId,
            DateTime lastModified,
            String description,
            int usage,
            String lists) {
        return new AutoValue_AlertListImpl(title, createdAt, creatorUserId,
                lastModified, description, usage, lists);
    }
}
