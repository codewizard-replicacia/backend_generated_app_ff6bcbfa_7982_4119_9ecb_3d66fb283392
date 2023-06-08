package com.mycompany.group234.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

@Converter(autoApply = false)
public class UUIDToByteConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(final UUID uuid) {

        return uuid == null ? null : uuid.toString();
    }

    private byte[] convertToBytes(final UUID uuid) {
        byte[] buffer = new byte[16];
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return buffer;
    }

    @Override
    public UUID convertToEntityAttribute(final String uuid)
    {
        return uuid == null ? null : convertToUUID(uuid);
    }

    private UUID convertToUUID(final String uuid) {
        return UUID.fromString(uuid);
    }
}
